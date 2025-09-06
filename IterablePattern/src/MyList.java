public class MyList<T> implements IterableCollection<T> {
    private T[] items;
    private int size;

    public MyList(T[] items) {
        this.items = items;
        this.size = items.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    // Inner Iterator Class
    private class MyListIterator implements Iterator<T> {
        private int index = 0;

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            if (!hasNext()) throw new RuntimeException("No more elements");
            return items[index++];
        }
    }
}