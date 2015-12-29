### 为什么数组的起始索引是0不是1？
   源于机器语言,那时要计算一个数组元素的地址，需要将数组的起始地址加上该元素的索引，
   将起始索引设为1要么浪费数组第一个元素的空间，要么花费额外的时间将索引减1。
   
   
### Maven + Spring MVC +Tomcat8.0 启动时错误：
	 A child container failed during startjava.util.concurrent.ExecutionException: org.apache.catalina.LifecycleException: Failed to start component           [StandardEngine[Catalina].StandardHost[localhost].StandardContext[/SpringMVC]]
   	解决方法:http://crunchify.com/org-apache-catalina-lifecycleexception/
   	添加一个commons-logging.jar包即可。

###SpringMVC-ajax  大量传递参数问题(因为angular的resource方法中数据多的时候有点问题，所以使用$ajax方式传递) 
	解决方案:
			== js code:
			var json = [
			              { "personId" : 1, "personName" :'a'},
			              { "personId" : 2, "personName" :'b'},
			              { "personId" : 3, "personName" :'c'}
			           ];
			  $.ajax({
		            type: "POST",
		            url: actionUrl.sendJson,
		            contentType: "application/json;charset=utf-8",
		            dataType: 'json',
		            data: JSON.stringify(json),
		            success: function (jsonResult) {
		            	console.log(jsonResult);
		            },error:function(jsonResult){
		            	console.log(jsonResult);
		            }
		        });
			== java controller:
			@RequestMapping(value = "/sendJson.do")
			@ResponseBody
			public String sendJson(HttpServletRequest request,@RequestBody List<PersonData> personData ){
				if(personData.size()>0){
					return "ok";
				}
				return "error";
			}
			== DTO
			public class PersonData implements Serializable{
				private Integer personId;
				private String personName;	
				//get/set
			}
			
			
	/**
	 * 栈:先进后出
	 * 栈的实现,可实现扩容,扩容机制与ArrayList扩容类似.后续还可继续完善!
	 * @date 2015-12-29
	 * @param <T>
	 */
	public class Stack<T>{
	public final static int CAPACITY = 10;//初始容量
	private T[] item;//当前元素数组
	private int CAPACITY_SIZE;//容量大小
	private int size ;//有数据的大小
	private int top = -1;//顶指针
	public Stack(){
		this(CAPACITY);
	}
	public Stack(int capacity){
		stackCapacity(capacity);
	}
	@SuppressWarnings("unchecked")
	public void stackCapacity(int capacity){
		if(capacity < 0){
			item = (T[]) new Object[CAPACITY];
			CAPACITY_SIZE = CAPACITY;
		}else{
				item = (T[]) new Object[capacity];
				CAPACITY_SIZE = capacity;
		}
	}
	//resize
	public void resize(){
		if(CAPACITY_SIZE < size+1){
			int  resize = size + (size >>> 1);
			CAPACITY_SIZE = resize;
			item = Arrays.copyOf(item, resize);
		}
	}
	//push
	public void push(T t){
		if(isOverflowed()){
			item[++top] = t;
			size ++;
		}else{
			resize();
			push(t);
		}
	}
	//pop
	public T pop(int index) throws Exception{
		T x = peek();
		item[top--] = null;
		size--;
		return x;
	}
	private T peek() throws Exception{
		if(isEmpty()) {throw new Exception("Stack is empty!");}
		return item[top];
	}
	//size
	public int getSize(){return size;}
	//isEmpty
	public boolean isEmpty(){return top == -1;}
	//overflowed
	public boolean isOverflowed(){
		int temp = top;
		return CAPACITY_SIZE > temp+1;
	}

	public String toString() {
		if (isEmpty())return "[ ]";
		StringBuffer out = new StringBuffer("[");
		for (int i = 0; i < top; i++)
			out.append(item[i] + ", ");
		out.append(item[top] + "]");
		return out.toString();
	}
	//	public static void main(String[] args) throws Exception {
	//		Stack<String> s = new Stack<String>();
	//		for (int i = 0; i < 10; i++){ 
	//			s.push(""+i);
	//		}
	//		System.out.println(s.toString());
	//		int size = s.getSize();
	//		for (int i = 0; i < size; i++){ 
	//			System.out.print(s.pop(i)+",");
	//		}
	//	}
	}		
			
   
