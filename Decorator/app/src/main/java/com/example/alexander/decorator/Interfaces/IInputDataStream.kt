package com.example.alexander.decorator.Interfaces

/**
 * Created by Alexander on 23.06.2016.
 */
interface IInputDataStream {
    // Возвращает признак достижения конца данных потока
    // Выбрасывает исключение std::ios_base::failuer в случае ошибки
    fun IsEOF(): Boolean
    // Считывает байт из потока.
    // Выбрасывает исключение std::ios_base::failure в случае ошибки
    fun ReadByte(): Byte
    // Считывает из потока блок данных размером size байт, записывая его в память
    // по адресу dstBuffer
    // Возвращает количество реально прочитанных байт. Выбрасывает исключение в случае ошибки
    fun ReadBlock(size: Long): Long
}