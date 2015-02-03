package csc.atd.ilab.labWorks.image;

import android.graphics.Bitmap;

import com.googlecode.tesseract.android.TessBaseAPI;

import csc.atd.ilab.labWorks.core.Params;

public class OcrController {
    private static OcrController controller = null;
    private String dataPath = null;

    private OcrController()
    {

    }

    public static OcrController getInstance()
    {
        if(controller == null) {
            controller = new OcrController();
            controller.initialize();
        }
        return controller;
    }

    private void initialize()
    {
        /*if (!(new File(Params.App_Folder_TessData + "\\" + Params.Ocr_Lang + ".traineddata")).exists()) {
            FileController.copyFile()
        }*/

        //ToDo :: Try finding Ocr Data or else throw error
        // hardcoded for time being
        dataPath = Params.App_Folder_TessData + "\\" + Params.Ocr_Lang + ".traineddata";
    }

    public String scanImage(Bitmap bitmap)
    {
        TessBaseAPI baseApi = new TessBaseAPI();
        baseApi.setDebug(true);
        baseApi.init(dataPath, Params.Ocr_Lang);
		/* Mat tmp = new Mat(bitmap.getWidth(), bitmap.getHeight(), 5 );
        Utils.bitmapToMat(bitmap, tmp);
         Mat gray = new Mat(bitmap.getWidth(), bitmap.getHeight(),4);
		 Mat mIntermediateMat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
		Mat mRgba = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
        Imgproc.cvtColor(tmp, gray, Imgproc.COLOR_RGB2GRAY);
		Imgproc.Canny(gray, mIntermediateMat, 35, 75);
		 Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 2, 2);
		Imgproc.cvtColor(mIntermediateMat, mRgba, Imgproc.COLOR_GRAY2BGRA, 4);
         Utils.matToBitmap(gray, bitmap);*/
        bitmap= ImageProc.convertToGrayScale(bitmap);
        baseApi.setImage(bitmap);

        String recognizedText = baseApi.getUTF8Text();

        baseApi.end();

        return recognizedText;
    }
}
