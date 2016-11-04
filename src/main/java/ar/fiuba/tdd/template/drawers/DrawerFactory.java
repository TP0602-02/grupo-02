package ar.fiuba.tdd.template.drawers;

import java.util.Properties;

/**
 * Created by Nicolas on 3/11/2016.
 */
public class DrawerFactory {

    private static DrawerFactory instance;
    private static Properties drawersTable;
    private static AbstractDrawer drawer;

    public static final String CLASSIC_DRAWER = "classic_drawer";
    public static final String COMPLEX_DRAWER = "complex_drawer";

    private DrawerFactory() {
        initTable();
    }


    public static DrawerFactory createDrawer(String drawerName) {
        if (instance == null) {
            instance = new DrawerFactory();
            initDrawer(drawerName);
        }
        return instance;
    }

    /**
     * Firstable must call createDrawer method.
     *
     * @return instance of DrawerFactory initialized.
     */
    public static DrawerFactory getInstance() {
        if (instance == null) {
            try {
                throw new Exception("Firstable must call createDrawer Method");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private static void initDrawer(String drawerName) {
        if (drawer == null) {
            drawer = (AbstractDrawer) drawersTable.getOrDefault(drawerName, drawersTable.get(CLASSIC_DRAWER));
        }
    }

    private static void initTable() {
        drawersTable = new Properties();
        drawersTable.put(CLASSIC_DRAWER, new ClassicDrawer());
        drawersTable.put(COMPLEX_DRAWER, new ComplexDrawer());
    }

    public AbstractDrawer getDrawer() {
        return drawer;
    }


}
