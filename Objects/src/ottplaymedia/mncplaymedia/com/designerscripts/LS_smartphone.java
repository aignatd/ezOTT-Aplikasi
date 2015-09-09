package ottplaymedia.mncplaymedia.com.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_smartphone{

public static void LS_480x800_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("pbmain").vw.setLeft((int)(0d));
views.get("pbmain").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pbmain").vw.setTop((int)((91d / 100 * height)));
views.get("pbmain").vw.setHeight((int)((100d / 100 * height) - ((91d / 100 * height))));
views.get("ptengah").vw.setLeft((int)(0d));
views.get("ptengah").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("ptengah").vw.setTop((int)((views.get("pamain").vw.getTop() + views.get("pamain").vw.getHeight())));
views.get("ptengah").vw.setHeight((int)((100d / 100 * height) - ((views.get("pamain").vw.getTop() + views.get("pamain").vw.getHeight()))));
views.get("lblgenre").vw.setLeft((int)((2d / 100 * width)));
views.get("lblgenre").vw.setHeight((int)((views.get("pamain").vw.getHeight())-(5d / 100 * height)));
views.get("lblgenre").vw.setTop((int)((views.get("pamain").vw.getHeight())/2d - (views.get("lblgenre").vw.getHeight() / 2)));
views.get("lblgenre").vw.setWidth((int)((25d / 100 * width)));
views.get("lblgenre").vw.setTop((int)((2d / 100 * height)));
views.get("lblgenre").vw.setHeight((int)((5d / 100 * height)));
views.get("lblfeat").vw.setHeight((int)((views.get("pamain").vw.getHeight())-(2d / 100 * height)));
views.get("lblfeat").vw.setWidth((int)((views.get("lblfeat").vw.getHeight())+(10d / 100 * width)));
views.get("lblfeat").vw.setTop((int)((views.get("pamain").vw.getHeight())/2d - (views.get("lblfeat").vw.getHeight() / 2)));
views.get("lblfeat").vw.setLeft((int)((98d / 100 * width) - (views.get("lblfeat").vw.getWidth())));
views.get("ivvod").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivfav").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivtvod").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivlivetv").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivlivetv").vw.setLeft((int)((1.8d / 100 * width)));
views.get("ivtvod").vw.setLeft((int)((views.get("ivlivetv").vw.getLeft() + views.get("ivlivetv").vw.getWidth())+(1.8d / 100 * width)));
views.get("ivvod").vw.setLeft((int)((views.get("ivtvod").vw.getLeft() + views.get("ivtvod").vw.getWidth())+(1.8d / 100 * width)));
views.get("ivfav").vw.setLeft((int)((views.get("ivvod").vw.getLeft() + views.get("ivvod").vw.getWidth())+(1.8d / 100 * width)));
views.get("ivlivetv").vw.setTop((int)((1d / 100 * height)));
views.get("ivtvod").vw.setTop((int)((views.get("ivlivetv").vw.getTop())));
views.get("ivvod").vw.setTop((int)((views.get("ivlivetv").vw.getTop())));
views.get("ivfav").vw.setTop((int)((views.get("ivlivetv").vw.getTop())));
views.get("ivfav").vw.setHeight((int)((7d / 100 * height)));
views.get("ivlivetv").vw.setHeight((int)((views.get("ivfav").vw.getHeight())));
views.get("ivtvod").vw.setHeight((int)((views.get("ivfav").vw.getHeight())));
views.get("ivvod").vw.setHeight((int)((views.get("ivfav").vw.getHeight())));

}
public static void LS_800x480_1(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("pamain").vw.setLeft((int)(0d));
views.get("pamain").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pamain").vw.setTop((int)(0d));
views.get("pamain").vw.setHeight((int)((11d / 100 * height) - (0d)));
views.get("pbmain").vw.setLeft((int)(0d));
views.get("pbmain").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pbmain").vw.setTop((int)((87.5d / 100 * height)));
views.get("pbmain").vw.setHeight((int)((100d / 100 * height) - ((87.5d / 100 * height))));
views.get("ptengah").vw.setLeft((int)(0d));
views.get("ptengah").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("ptengah").vw.setTop((int)((views.get("pamain").vw.getTop() + views.get("pamain").vw.getHeight())));
views.get("ptengah").vw.setHeight((int)((100d / 100 * height) - ((views.get("pamain").vw.getTop() + views.get("pamain").vw.getHeight()))));
views.get("lblgenre").vw.setLeft((int)((2d / 100 * width)));
views.get("lblgenre").vw.setHeight((int)((views.get("pamain").vw.getHeight())-(5d / 100 * height)));
views.get("lblgenre").vw.setTop((int)((views.get("pamain").vw.getHeight())/2d - (views.get("lblgenre").vw.getHeight() / 2)));
views.get("lblgenre").vw.setWidth((int)((15d / 100 * width)));
views.get("lblfeat").vw.setHeight((int)((views.get("pamain").vw.getHeight())-(2d / 100 * height)));
views.get("lblfeat").vw.setWidth((int)((views.get("lblfeat").vw.getHeight())+(10d / 100 * width)));
views.get("lblfeat").vw.setTop((int)((views.get("pamain").vw.getHeight())/2d - (views.get("lblfeat").vw.getHeight() / 2)));
views.get("lblfeat").vw.setLeft((int)((98d / 100 * width) - (views.get("lblfeat").vw.getWidth())));
views.get("ivvod").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivfav").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivtvod").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivlivetv").vw.setWidth((int)((22.5d / 100 * width)));
views.get("ivlivetv").vw.setLeft((int)((1.8d / 100 * width)));
views.get("ivtvod").vw.setLeft((int)((views.get("ivlivetv").vw.getLeft() + views.get("ivlivetv").vw.getWidth())+(1.8d / 100 * width)));
views.get("ivvod").vw.setLeft((int)((views.get("ivtvod").vw.getLeft() + views.get("ivtvod").vw.getWidth())+(1.8d / 100 * width)));
views.get("ivfav").vw.setLeft((int)((views.get("ivvod").vw.getLeft() + views.get("ivvod").vw.getWidth())+(1.8d / 100 * width)));
views.get("ivlivetv").vw.setTop((int)((1d / 100 * height)));
views.get("ivtvod").vw.setTop((int)((views.get("ivlivetv").vw.getTop())));
views.get("ivvod").vw.setTop((int)((views.get("ivlivetv").vw.getTop())));
views.get("ivfav").vw.setTop((int)((views.get("ivlivetv").vw.getTop())));
views.get("ivfav").vw.setHeight((int)((10.5d / 100 * height)));
views.get("ivlivetv").vw.setHeight((int)((views.get("ivfav").vw.getHeight())));
views.get("ivtvod").vw.setHeight((int)((views.get("ivfav").vw.getHeight())));
views.get("ivvod").vw.setHeight((int)((views.get("ivfav").vw.getHeight())));

}
public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("gifsmart").vw.setTop((int)((50d / 100 * height) - (views.get("gifsmart").vw.getHeight() / 2)));
views.get("gifsmart").vw.setLeft((int)((50d / 100 * width) - (views.get("gifsmart").vw.getWidth() / 2)));
views.get("pamain").vw.setLeft((int)(0d));
views.get("pamain").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("pamain").vw.setTop((int)(0d));
views.get("pamain").vw.setHeight((int)((9d / 100 * height) - (0d)));

}
}