package sharkbound.forge.shared.util.classes

import kotlin.reflect.KProperty

class IndexVal<T>(val index: Int, val list: MutableList<T>) {
    operator fun getValue(ref: Any?, prop: KProperty<*>) =
            list[index]
}