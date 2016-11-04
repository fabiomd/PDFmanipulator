package View;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Classes.data;

/*
 * @Author: F�bio Moreira Duarte
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
        JButton button = SetJButton("Gerar Relat�rio",getButtonSize(),new Point(GetDimensionExcaleHeight(.2f),GetDimensionExcaleWidth(.06f)),RIGHT_ALIGNMENT);
        button.addActionListener(new actionGenerate(datafiles));
        jPanel.add(button);
        JButton button2 = SetJButton("Resetar Sele��o",getButtonSize(),new Point(GetDimensionExcaleHeight(.2f),GetDimensionExcaleWidth(1.1f)),RIGHT_ALIGNMENT);
        button2.addActionListener(new actionReset(datafiles));
        jPanel.add(button2);
        JButton button3 = SetJButton("Selecionar SubMenu",getButtonSize(),new Point(GetDimensionExcaleHeight(.2f),GetDimensionExcaleWidth(.9f)),RIGHT_ALIGNMENT);
        button3.addActionListener(new actionMaxSelect(datafiles,true));
        jPanel.add(button3);
        JButton button4 = SetJButton("Desselecionar SubMenu",getButtonSize(),new Point(GetDimensionExcaleHeight(.2f),GetDimensionExcaleWidth(.7f)),RIGHT_ALIGNMENT);
        button4.addActionListener(new actionMaxSelect(datafiles,false));
        jPanel.add(button4);
	}
}
