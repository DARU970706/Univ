public class BlogTest {
	
		static int b;
		
		public static void main(String[] args) throws Exception{
			b=-9;
			test();
		}
		
        public static void test() {
                SomeClass someClass = new SomeClass(someCallBack);
                someClass.doSome();
        }

        private static CallBack someCallBack = new CallBack() {
                @Override
                public void callBackMethod(Object obj) {

                        String result = (String) obj;

                        System.out.println(result);
                        System.out.println(b);
                }
        };

}