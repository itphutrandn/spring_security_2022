package com.eazybytes.controller;

public class note {

//    I have answered it here also. To understand the role of OncePerRequestFilter, we need to first clearly understand how a normal filter behaves. When you want some specific code to execute just before or after servlet execution, you create a filter which works as:
//
//    code1   ===>   servlet execution (using chain.doFilter())   ===>    code2
//    So code1 executes before servlet and code2 after servlet execution. But here, while servlet execution, there can be some other request to a different servlet and that different servlet is also having this same filter. In this case, this filter will execute again.
//
//    OncePerRequestFilter prevents this behavior. For our one request, this filter will execute exactly one time (no more no less). This behavior is very useful while working with security authentication.
}
