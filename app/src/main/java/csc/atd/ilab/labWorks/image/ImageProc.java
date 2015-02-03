package csc.atd.ilab.labWorks.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import csc.atd.ilab.labWorks.store.FileController;


public class ImageProc {

    public static void convertToGrayScale(String path)
    {

        Bitmap photoBitmap = BitmapFactory.decodeFile(path);
        Mat tmpMat = new Mat (photoBitmap.getWidth(), photoBitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(photoBitmap, tmpMat);
        //Mat imgToProcess=Utils.bitmapToMat(photoBitmap);

        //Imgproc.cvtColor(imgToProcess, imgToProcess, Imgproc.COLOR_BGR2GRAY);
        //Imgproc.cvtColor(imgToProcess, imgToProcess, Imgproc.COLOR_GRAY2RGBA, 4);

        Imgproc.cvtColor(tmpMat, tmpMat, Imgproc.COLOR_RGB2GRAY);

        Imgproc.cvtColor(tmpMat, tmpMat, Imgproc.COLOR_GRAY2RGB, 4);
        Utils.matToBitmap(tmpMat, photoBitmap);

        FileController.saveBitmap(photoBitmap);

    }


}