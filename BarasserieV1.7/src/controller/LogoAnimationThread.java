package controller;

public class LogoAnimationThread extends Thread {
    private static final int FRAME_COUNT = 10;
    private static final int FRAME_DURATION = 100;

    private AdminController adminController;

    public LogoAnimationThread(AdminController adminController) {
        this.adminController = adminController;
    }

    @Override
    public void run() {
        double opacity;
        while (true) {
            try {
                for (int i = FRAME_COUNT; i > 0; i--) {
                    opacity = (double) i / FRAME_COUNT;
                    adminController.setLogoOpacity(opacity);
                    adminController.setStartMessageOpacity(opacity);
                    
                    Thread.sleep(FRAME_DURATION);
                }
                for (int i = 1; i <= FRAME_COUNT; i++) {
                    opacity = (double) i / FRAME_COUNT;
                    adminController.setLogoOpacity(opacity);
                    adminController.setStartMessageOpacity(opacity);

                    Thread.sleep(FRAME_DURATION);
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
