package skeleton;

public class Observer {
	private int id;
	private Subject subject;

	public Observer(int id) {
		this.id = id;
	}

	public int getID(){
		return id;
	}
	
	public void subscribe(Subject sub) {
		this.subject = sub;
	}
	
	public void unsubscribe() {
		subject.unregister(this);
	}
	
	public void update(){
		// TODO: The observer will exit the queue 
		// once the notification has value >= this.id+7.
		// Don't forget they will leave if it is their number too.
		String order = subject.getMessage();
		int get_id = 0;
		for(int i = 0; i < order.length(); ++i){
			char character =  order.charAt(i);
			if(character >= 0 && character <= 9){
				get_id *= 10;
				get_id += character;
			}
		}
		
		if(this.id == get_id){
			System.out.println(String.format("I get the drink, id = %d, and the order = %d", this.id, get_id));
		}else if(get_id >= this.id + 7){
			System.out.println(String.format("Too slow!!! my id = %d, and the order = %d", this.id, get_id));
		}else{
			return;
		}
		unsubscribe();
	}
}
