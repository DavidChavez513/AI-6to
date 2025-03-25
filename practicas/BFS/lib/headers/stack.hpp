#ifndef stack_hpp
#define stack_hpp

#include <iostream>
#include <cassert>
#include <node.hpp>

template <typename T>

class stack
{
private:
    int n; // stack capacity
    int s; // stack size

    node<T> *list;

public:
    stack(int, int);

    void push(T);
    void pop();
    int top();

    int capacity();
    int size();

    bool full() const { return s == n; }
    bool size() const { return s == 0; }

    ~stack();
};

#endif