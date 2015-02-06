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
        dataPath = Params.App_Folder_Root ;
    }

    public String scanImage(Bitmap bitmap)
    {
        TessBaseAPI baseApi = new TessBaseAPI();
        baseApi.setDebug(true);
        baseApi.init(dataPath, Params.Ocr_Lang);
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        bitmap =ImageProc.convertToGrayScale(bitmap);
        baseApi.setImage(bitmap);

        String recognizedText = baseApi.getUTF8Text();

        baseApi.end();

        return recognizedText;
    }
}
