package vista;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;

public class JPFichas extends JPanel {


	public JPFichas(String titulo) {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		setBorder(new TitledBorder(null, titulo , TitledBorder.LEADING, TitledBorder.TOP, null, null));

	}

}
