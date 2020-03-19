package sharkbound.forge.shared.util

class Incrementer(val modulus: Int) {
    private var current = 0

    fun next(): Boolean {
        current += 1
        if (current % modulus == 0) {
            current = 0
            return true
        }
        return false
    }
}