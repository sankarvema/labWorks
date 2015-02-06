package csc.atd.ilab.labWorks.image;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import csc.atd.ilab.labWorks.store.FileController;


public class ImageProc {


    public static Bitmap convertToGrayScale(Bitmap photoBitmap)
    {


        Mat tmpMat = new Mat (photoBitmap.getWidth(), photoBitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(photoBitmap, tmpMat);

        Imgproc.cvtColor(tmpMat, tmpMat, Imgproc.COLOR_RGB2GRAY);


        Utils.matToBitmap(tmpMat, photoBitmap);

        FileController.saveBitmap(photoBitmap);
        return photoBitmap;
    }



}