package csc.atd.ilab.labWorks.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import android.util.Log;
import csc.atd.ilab.labWorks.store.FileController;


public class ImageProc {


    public static Bitmap convertToGrayScale(Bitmap photoBitmap)
    {


        Mat tmpMat = new Mat (photoBitmap.getWidth(), photoBitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(photoBitmap, tmpMat);
        //Mat imgToProcess=Utils.bitmapToMat(photoBitmap);

        //Imgproc.cvtColor(imgToProcess, imgToProcess, Imgproc.COLOR_BGR2GRAY);
        //Imgproc.cvtColor(imgToProcess, imgToProcess, Imgproc.COLOR_GRAY2RGBA, 4);

        Imgproc.cvtColor(tmpMat, tmpMat, Imgproc.COLOR_RGB2GRAY);

        Imgproc.cvtColor(tmpMat, tmpMat, Imgproc.COLOR_GRAY2RGB, 4);
        Utils.matToBitmap(tmpMat, photoBitmap);

        FileController.saveBitmap(photoBitmap);
        return photoBitmap;
    }

   /* public static Bitmap convertToGrayScale(Bitmap src)
    {
        // constant factors
        final double GS_RED = 0.299;
        final double GS_GREEN = 0.587;
        final double GS_BLUE = 0.114;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // take conversion up to one single value
                R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final image
        return bmOut;
    }*/


}