#ifndef node_hpp
#define node_hpp

#include <iostream>
#include <cassert>

using namespace std;

template <typename T>

class node
{
private:
    T payload;
    node<T> *_next;

public:
    node(T);

    T data() const { return payload; }
    node *next() const { return _next; }
    node *next(node *p) const { return _next = p; }

    ~node();
};

#endif