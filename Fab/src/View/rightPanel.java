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
	public rightPanel(Dimension dimension,data data) {
		super(dimension,data);
	}

	private static final long serialVersionUID = 1L;

	protected void SetJPanel(JPanel jPanel,BoxLayout boxlayout,Point point,Dimension dimension){
		jPanel.setLayout(null);
		jPanel.setLocation(point);
		jPanel.setSize(dimension);
	}
	
	//inicialize it
	public void Inicialize() {
		BoxLayout boxlayoutRight = createBox(jPanel);
        SetJPanel(jPanel,boxlayoutRight,new Point(442,0),new Dimension(511,609));
        JButton button = SetJButton("Gerar Relatorio",new Dimension(200,50),new Point(300,10),RIGHT_ALIGNMENT);
        button.addActionListener(new actionGenerate(datafiles));
        jPanel.add(button);
        JButton button2 = SetJButton("Resetar Selecao",new Dimension(200,50),new Point(300,486),RIGHT_ALIGNMENT);
        button2.addActionListener(new actionReset(datafiles));
        jPanel.add(button2);
	}
}
