public class MainGeneric {
    public static void main(String[] args) {
        // 사용 예
        BoxGeneric<Integer> integerBox = new BoxGeneric<>();
        BoxGeneric<String> stringBox = new BoxGeneric<>();

        integerBox.set(10);
        stringBox.set("Hello World");

        System.out.println(integerBox.get());
        System.out.println(stringBox.get());

        // 사용 예
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};

        UtilGeneric.printArray(intArray);
        UtilGeneric.printArray(stringArray);
    }
}
