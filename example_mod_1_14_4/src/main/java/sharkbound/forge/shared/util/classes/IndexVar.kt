package sharkbound.forge.shared.util.classes

import kotlin.reflect.KProperty

class IndexVar<T>(val index: Int, val list: MutableList<T>) {
    operator fun getValue(ref: Any?, prop: KProperty<*>) =
            list[index]

    operator fun setValue(ref: Any?, prop: KProperty<*>, value: T) {
        list[index] = value
    }
}