package org.cboard.services.persist.excel;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by yfyuan on 2017/2/15.
 */
public class JpgXlsProcesser extends XlsProcesser {
    @Override
    protected ClientAnchor drawContent(XlsProcesserContext context) {
        String pngData = context.getData().getString("data");
        byte[] bytes = Base64.getDecoder().decode(pngData.substring(23));
        int pictureIdx = context.getWb().addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        Drawing drawing = context.getBoardSheet().createDrawingPatriarch();
        CreationHelper helper = context.getWb().getCreationHelper();
        ClientAnchor anchor = helper.createClientAnchor();
//        anchor.setCol1(context.getC1());
//        anchor.setRow1(context.getR1());

        // FIXME!!!
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
            anchor.setCol1(context.getC1());
            anchor.setRow1(context.getR1());
            anchor.setCol2(context.getC2());
            anchor.setRow2((int)(0.13/0.18 * image.getHeight() / image.getWidth() * (context.getC2() - context.getC1()) + context.getR1()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Picture picture = drawing.createPicture(anchor, pictureIdx);
//        picture.resize();
        picture.resize(1.0);
        return picture.getClientAnchor();
    }
}
