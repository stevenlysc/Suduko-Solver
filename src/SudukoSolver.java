import java.awt.EventQueue;


public class SudukoSolver {
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SudukoGUI sudukoGUI = new SudukoGUI();
                sudukoGUI.setVisible(true);
            }
        });
	}
}
