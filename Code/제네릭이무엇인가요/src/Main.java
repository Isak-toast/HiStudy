public class Main {
    public static void main(String[] args) {
        // 사용 예
        Box integerBox = new Box();
        Box stringBox = new Box();

        integerBox.set(10);
        stringBox.set("Hello World");

        Integer intValue = (Integer) integerBox.get(); // 캐스팅 필요
        String stringValue = (String) stringBox.get(); // 캐스팅 필요

        System.out.println(intValue);
        System.out.println(stringValue);

        // 사용 예
        Object[] intArray = {1, 2, 3};
        Object[] stringArray = {"Hello", "World"};

        Util.printArray(intArray);
        Util.printArray(stringArray);

    }
}
