
public class ConnectionLine extends UMLObject{
	protected UMLObject start_object;
	protected UMLObject end_object;
	
	//name代表這條連接線的名字
	public ConnectionLine(String name, UMLObject start_object, UMLObject end_object){
		this.name = name;
		this.start_object = start_object;
		this.end_object = end_object;
		//ports代表連接兩個物件的兩個點
		ports = new Port[2];
		drawports = true;
	}
	
	protected void refreshConnectionPoint(){
		Port[] start_object_ports = start_object.getPorts();
		Port[] end_object_ports = end_object.getPorts();
		int distance = Integer.MAX_VALUE;
		//start_object_ports.length=4,end_object_ports.lenght=4
		//去算說兩個物件中要用哪兩個點連結物件的距離可以最短
		for(int i = 0; i < start_object_ports.length; i++){
			for(int j = 0; j < end_object_ports.length; j++){
				int new_distance = calculateDistance(start_object_ports[i], end_object_ports[j]);
				if(new_distance < distance){
					distance = new_distance;
					ports[0] = start_object_ports[i];
					ports[1] = end_object_ports[j];
				}
			}
		}
	}
}
