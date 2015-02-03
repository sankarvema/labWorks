package csc.atd.ilab.labWorks.store;


import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import csc.atd.ilab.labWorks.core.Params;

public class FileController {
    public static void saveBitmap(Bitmap bitmapToSave)
    {
        File myDir=new File(Params.App_Folder_Images);
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Grayscale-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmapToSave.compress(Bitmap.CompressFormat.JPEG, 600, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
