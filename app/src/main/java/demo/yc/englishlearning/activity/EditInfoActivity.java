package demo.yc.englishlearning.activity;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import demo.yc.englishlearning.R;
import demo.yc.englishlearning.entity.User;
import demo.yc.lib.utils.CommonUtil;
import io.reactivex.functions.Consumer;

public class EditInfoActivity extends BaseAppActivity
{

    @BindView(R.id.edit_icon)
    ImageView mIcon;
    @BindView(R.id.edit_input)
    EditText mInput;
    User mUser;

    String mPath;
    String mName;

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_edit_info;
    }

    @Override
    protected void initEvents()
    {
        setTitle(getResources().getString(R.string.toolbar_collection));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mUser = mApp.getLoginUser();
        mPath = mUser.getIconPath();
        Log.w("edit", "initEvents: " + mUser.toString());
        Glide.with(this).load(mUser.getIconPath())
                .placeholder(R.mipmap.ic_launcher)
                .into(mIcon);
        mInput.setText(mUser.getUserNickName());

    }


    /**
     *
     */
    @OnClick(R.id.edit_change)
    public void onMEditChangeClicked()
    {
        // 权限申请
        doRequest();

    }

    /**
     * 申请访问sd卡权限
     */
    private void doRequest()
    {
        RxPermissions rxPermission =
                new RxPermissions(EditInfoActivity.this);
        rxPermission.requestEach(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>()
                {
                    @Override
                    public void accept(Permission permission) throws Exception
                    {
                        if (permission.granted)
                        {
                            // 用户已经同意该权限
                            toSDImage();
                        } else if (permission.shouldShowRequestPermissionRationale)
                        {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Toast.makeText(EditInfoActivity.this,
                                    "请允许获取权限", Toast.LENGTH_SHORT).show();
                        } else
                        {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Toast.makeText(EditInfoActivity.this,
                                    "请到手机设置中开启权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 跳转到手机图片界面
     */
    private void toSDImage()
    {
        Intent intent = new Intent();

                /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");

                /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);

                /* 取得相片后返回本画面 */
        startActivityForResult(intent, 1);
    }

    @OnClick(R.id.edit_save_btn)
    public void onMEditSaveBtnClicked()
    {
        mName = mInput.getText().toString();
        if (CommonUtil.isEmpty(mName))
        {
            Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
        } else
        {
            mUser.setIconPath(mPath);
            mUser.setUserNickName(mName);
            boolean result = mUser.save();
            Log.w("save", "onMEditSaveBtnClicked: " + "保存状态--》" + result);
            mApp.setLoginUser(mUser);
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            mPath = getRealPathFromUriAboveApi19(
                    this, data.getData());
            Log.w("imagePath", "onActivityResult: ===>" + mPath);
            Glide.with(this).load(mPath)
                    .placeholder(R.mipmap.ic_launcher).thumbnail(0.5f).into(mIcon);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 根据sdk版本，获取图片的绝对路径
     *
     * @param context
     * @param uri
     * @return
     */
    private static String getRealPathFromUriAboveApi19(Context context, Uri uri)
    {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri))
        {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri))
            { // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection, selectionArgs);
            } else if (isDownloadsDocument(uri))
            { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri,
                        null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme()))
        {
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme()))
        {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        return filePath;
    }


    /**
     * 取数据库中获取路径字段
     *
     * @param context
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs)
    {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try
        {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst())
            {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e)
        {
            if (cursor != null)
            {
                cursor.close();
            }
        }
        return path;
    }


    /**
     * 多媒体库选取的图片
     *
     * @param uri
     * @return
     */
    private static boolean isMediaDocument(Uri uri)
    {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * 下载库选择的图片
     *
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri)
    {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


}