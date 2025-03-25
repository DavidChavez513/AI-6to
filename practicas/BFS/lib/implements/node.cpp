#include "../headers/node.hpp"

template <typename T>

node::node(T data) {
    this.payload = data;
    this._next = nullptr;
}
