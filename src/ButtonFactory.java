
public class ButtonFactory {
	
	public UMLbutton getButton(String name){
		UMLbutton umlbutton=null;
		switch(name){
			case Configure.SelectBut_title:
				umlbutton = new SelectBut();
				break;
			case Configure.AssociationLineBut_title:
				umlbutton = new AssociationLineBut();
				break;
			case Configure.GeneralizationLineBut_title:
				umlbutton = new GeneralizationLineBut();
				break;
			case Configure.CompositionLineBut_title:
				umlbutton = new CompositionLineBut();
				break;
			case Configure.ClassBut_title:
				umlbutton = new ClassBut();
				break;
			case Configure.UseCaseBut_title:
				umlbutton = new UseCaseBut();
				break;
		}
		return umlbutton;
	}
}
