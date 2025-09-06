
public class IteratorDemo {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>(new String[]{"A", "B", "C"});
        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
