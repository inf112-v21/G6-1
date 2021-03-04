package inf112.skeleton.app.shared;

public enum Color {
        GREEN(0),
        GREY(1),
        ORANGE(2),
        PINK(3),
        PURPLE(4);

        private float color;

        public float getAction(){
                return this.color;
        }
         Color(float color){

                this.color = color;
        }
}
