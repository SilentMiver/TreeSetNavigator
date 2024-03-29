package NavigatorAPI.utils.Set;

public interface Set<E> {

    /**
     * Добавляет элемент в множество.
     *
     * @param element элемент для добавления
     * @return true, если элемент был добавлен, false, если элемент уже присутствует в множестве
     */
    boolean add(E element);

    /**
     * Удаляет элемент из множества.
     *
     * @param element элемент для удаления
     * @return true, если элемент был удален, false, если элемент не присутствовал в множестве
     */
    boolean remove(E element);

    /**
     * Проверяет наличие элемента в множестве.
     *
     * @param element элемент для проверки
     * @return true, если элемент присутствует в множестве, false, если элемент не присутствует в множестве
     */
    boolean contains(E element);

    /**
     * Возвращает количество элементов в множестве.
     *
     * @return количество элементов в множестве
     */
    int size();

    /**
     * Очищает множество.
     */
    void clear();
}
