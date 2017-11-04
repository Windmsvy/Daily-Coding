// 单例
// 1
public class Singleton{
	private static Singleton _instance = null;

	private Singleton(){

	}
	public static Singleton getInstance(){
		if(_instance == null){
			_instance = new Singleton();
		}
		return _instance;
	}
}

// 2

public class Singleton{
	private static Singleton _instance = null;
	private Singleton(){}
	public static synchronized Singleton getInstance(){
		synchronized(this){
			if(_instance == null){
				_instance = new Singleton();
			}
		}
		return _instance;
	}
}

//3

public class Singleton{
	private static Singleton _instance = new Singleton();
	private Singleton(){}
	public static Singleton getInstance(){
		return _instance;
	}
}

//4

public enum Foo {
     INSTANCE;
     // public Foo getInstance(){ return INSTANCE; }
}

//5

public class Singleton{
	private static class SingletonHolder{
		private static final Singleton _instance = new Singleton();
	}
	private Singleton(){}
	public static Singleton getInstance(){
		return SingletonHolder._instance;
	}
}
