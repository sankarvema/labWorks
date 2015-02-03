package csc.atd.ilab.labWorks.core;

import android.os.Environment;
import csc.atd.ilab.labWorks.R;

public class Params
{
    public static final String App_Folder_Root = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/LabWorks";

    public static final String App_Folder_Images =  App_Folder_Root + "/Images";
    public static final String App_Folder_TessData =  App_Folder_Root + "/TessData";

    public static final String App_Tag = "@" + R.string.app_tag;
    public static final String Ocr_Lang = "eng";
}