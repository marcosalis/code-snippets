package com.github.marcosalis.training.snippets.concurrency;

/**
 * (JLS 17.4) The semantics of the Java programming language allow compilers and microprocessors to
 * perform optimizations that can interact with incorrectly synchronized code in ways that can
 * produce behaviors that seem paradoxical.
 *
 * (...) A Just-In-Time compiler in a Java Virtual Machine implementation may rearrange code, or the
 * processor. In addition, the memory hierarchy of the architecture on which a Java Virtual Machine
 * implementation is run may make it appear as if code is being reordered. (...) we shall refer to
 * anything that can reorder code as a compiler.
 */
@SuppressWarnings("unused")
public class ReorderingExample {

    private int A = 0;
    private int B = 0;

    // this is executed in thread 1
    private void assignmentFirst() {
        // t1
        int r2 = A;
        // t2
        B = 1;
    }

    // this is executed after assignmentFirst() in thread 2
    private void assignmentLast() {
        // t3
        int r1 = B;
        // t4
        A = 2;
    }

    // what is the final result?
    // is r2 == 2 and r1 == 1 ever a possible outcome?

    /**
     * Possible compiler instructions reordering:
     *
     * Thread 1:
     * B = 1
     * r2 = A
     *
     * Thread 2:
     * r1 = B
     * A = 2
     */

}