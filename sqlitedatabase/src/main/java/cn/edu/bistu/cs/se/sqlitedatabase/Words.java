package cn.edu.bistu.cs.se.sqlitedatabase;


import android.net.Uri;
import android.provider.BaseColumns;

public class Words {
    public static final String AUTHORITY = "cn.edu.bistu.cs.se.provider";//授权者
    public Words(){

    }
    public static abstract class Word implements BaseColumns{
        public static final String TABLE_NAME="words";//定义表名
        public static final String COLUMN_NAME_WORD="word";//定义列名
        public static final String COLUMN_NAME_MEANING="meaning";
        public static final String COLUMN_NAME_SAMPLE ="sample";
        public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";
        public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";
        public static final String MINE_ITEM = "vnd.bistu.cs.se.word";

        public static final String MINE_TYPE_SINGLE = MIME_ITEM_PREFIX + "/" + MINE_ITEM;
        public static final String MINE_TYPE_MULTIPLE = MIME_DIR_PREFIX + "/" + MINE_ITEM;

        public static final String PATH_SINGLE = "word/#";//单条数据的路径
        public static final String PATH_MULTIPLE = "word";//多条数据的路径

        //Content Uri
        public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

    }

}