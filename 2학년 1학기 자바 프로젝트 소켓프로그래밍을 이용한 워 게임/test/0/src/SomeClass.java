public class SomeClass {

        private CallBack someCallBack;

        public SomeClass(CallBack someCallBack) {
                this.someCallBack = someCallBack;
        }

        public void doSome() {

                // working...

                String result = "Hello CallBack";

                someCallBack.callBackMethod(result);

        }
}