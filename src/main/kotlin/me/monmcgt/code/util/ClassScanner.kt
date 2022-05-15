package me.monmcgt.code.util

import java.io.File

class ClassScanner private constructor() {
    companion object {
        @JvmStatic
        private val INSTANCE = ClassScanner()

        @JvmStatic
        fun getInstance() = INSTANCE
    }

    private val classLoader = Thread.currentThread().contextClassLoader

    fun getAllClasses(packageName: String = ""): List<Class<*>> {
        val classes = mutableListOf<Class<*>>()
        val classPath = classLoader.getResource(packageName.replace(".", "/"))?.path
        val classFiles = classPath?.let { File(it).listFiles() }
        for (file in classFiles ?: emptyArray()) {
            if (file.isFile && file.name.endsWith(".class")) {
                val className = file.name.substring(0, file.name.length - 6)
                try {
                    classes.add(Class.forName(if (packageName.isEmpty()) className else "$packageName.$className"))
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                }
            } else if (file.isDirectory) {
                classes.addAll(getAllClasses(if (packageName.isEmpty()) file.name else "$packageName.${file.name}"))
            } else {
                // file but not a class file
            }
        }
        return classes
    }
}