#ifndef queue_hpp
#define queue_hpp
#include <iostream>
#include <cassert>
#include <node.hpp>

template <typename T>

class queue
{
private:
    int n;
    int s;

    node<T> *front, *back;

public:
    queue(int);
    ~queue();

    int capacity() const { return n; }
    int size() const { return s; }

    bool empty() const { return s == 0; }
    bool full() const { return s == n; }

    void enqueue(int);
    void dequeue();
    int front();
    void print();
};

#endif