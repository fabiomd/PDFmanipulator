package View;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Classes.data;

/*
 * @Author: Fábio Moreira Duarte
 * This class defines a panel
 * */

public class rightPanel extends panel{

	//needs a dimension and a data
	public rightPanel(Dimension screenDimension,data data,Point point,Dimension dimension) {
		super(screenDimension,data,point,dimension);
	}

	private static final long serialVersionUID = 1L;

	protected void SetJPanel(JPanel jPanel,BoxLayout boxlayout,Point point,Dimension dimension){
		jPanel.setLayout(null);
		jPanel.setLocation(point);
		jPanel.setSize(dimension);
	}
	
	private Dimension getButtonSize(){
		return new Dimension(GetDimensionExcaleHeight(.3f),GetDimensionExcaleWidth(.2f));
	}
	
	//inicialize it
	public void Inicialize() {
		BoxLayout boxlayoutRight = createBox(jPanel);
        SetJPanel(jPanel,boxlayoutRight,point,dimension);
        JButton button = SetJButton("Gerar Relatório",getButtonSize(),new Point(GetDimensionExcaleHeight(.05f),GetDimensionExcaleWidth(.06f)),RIGHT_ALIGNMENT);
        button.addActionListener(new actionGenerate(datafiles));
        jPanel.add(button);
        JButton button2 = SetJButton("Resetar Seleção",getButtonSize(),new Point(GetDimensionExcaleHeight(.05f),GetDimensionExcaleWidth(1.6f)),RIGHT_ALIGNMENT);
        button2.addActionListener(new actionReset(datafiles));
        jPanel.add(button2);
	}
}
