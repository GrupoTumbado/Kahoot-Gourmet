package pw.espana.kahootgourmet.commons.game;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SerializableObservableList<E> implements ObservableList<E>, Serializable {
    private transient ObservableList<E> list;

    public SerializableObservableList() {
        list = FXCollections.observableArrayList();
    }

    public SerializableObservableList(ObservableList<E> list) {
        this.list = list;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public java.util.Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return list.toArray(ts);
    }

    @Override
    public boolean add(E e) {
        return list.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return list.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return list.addAll(collection);
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        return list.addAll(i, collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return list.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return list.retainAll(collection);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public E get(int i) {
        return list.get(i);
    }

    @Override
    public E set(int i, E e) {
        return list.set(i, e);
    }

    @Override
    public void add(int i, E e) {
        list.add(i, e);
    }

    @Override
    public E remove(int i) {
        return list.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public java.util.ListIterator<E> listIterator() {
        return list.listIterator();
    }

    @Override
    public java.util.ListIterator<E> listIterator(int i) {
        return list.listIterator(i);
    }

    @Override
    public java.util.List<E> subList(int i, int i1) {
        return list.subList(i, i1);
    }

    @Override
    public void addListener(ListChangeListener<? super E> listener) {
        list.addListener(listener);
    }

    @Override
    public void removeListener(ListChangeListener<? super E> listener) {
        list.removeListener(listener);
    }

    @Override
    public boolean addAll(E... elements) {
        return list.addAll(elements);
    }

    @Override
    public boolean setAll(E... elements) {
        return list.setAll(elements);
    }

    @Override
    public boolean setAll(Collection<? extends E> col) {
        return list.setAll(col);
    }

    @Override
    public boolean removeAll(E... elements) {
        return list.removeAll(elements);
    }

    @Override
    public boolean retainAll(E... elements) {
        return list.retainAll(elements);
    }

    @Override
    public void remove(int from, int to) {
        list.remove(from, to);
    }

    @Override
    public boolean removeIf(java.util.function.Predicate<? super E> filter) {
        return list.removeIf(filter);
    }

    @Override
    public void forEach(java.util.function.Consumer<? super E> action) {
        list.forEach(action);
    }

    @Override
    public void replaceAll(java.util.function.UnaryOperator<E> operator) {
        list.replaceAll(operator);
    }

    @Override
    public void sort(java.util.Comparator<? super E> c) {
        list.sort(c);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        List<E> tempList = (List<E>) s.readObject();

        if (list != null) {
            list.clear();
        } else {
            list = FXCollections.observableArrayList();
        }

        for (E element : tempList) {
            if (element instanceof Question) {
                list.add((E) ((Question) element).clone());
            } else {
                list.add(element);
            }
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(list));
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        list.addListener(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        list.removeListener(invalidationListener);
    }
}
