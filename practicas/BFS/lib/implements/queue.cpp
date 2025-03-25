#include "../headers/queue.hpp"
#include <cassert>

template <typename T>

queue::queue(T _n)
{
    this.n = _n;
    this.s = 0;
    this.front = nullptr;
    this.back = nullptr;
}

queue::~queue()
{
    // Investigar los destructores
}

void queue::enqueue(int x)
{
    assert(!full());
    node<T> *aux = new node(x);
    if (empty())
    {
        front = aux;
        back = aux;
    }
    else
    {
        back->next(aux);
        back = aux;
    }

    s++;
}

void queue::dequeue()
{
    assert(!empty());
    node *aux = front;
    front = front->next();
    s--;
    delete aux;
}

int queue::front()
{
    assert(!empty());
    return front->data();
}

void queue::print()
{
    std::cout << "[";

    node *p = front;

    while (p)
    {
        std::cout << p->data() << " ";
        p = p->next();
    }

    std::cout << "]" << std::endl;
}