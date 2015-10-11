// ConsoleApplication1.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include "stdio.h"
#include "stdlib.h"
#include "time.h"
#include "windows.h"

int main()
{
	//time to read context from hard disk 
	FILE *fp;
	printf("Time to read a file from hard disk is ");
	LARGE_INTEGER m_liPerfFreq = { 0 };
	QueryPerformanceFrequency(&m_liPerfFreq);

	LARGE_INTEGER m_liPerfStart = { 0 };
	LARGE_INTEGER liPerfNow = { 0 };
	QueryPerformanceCounter(&m_liPerfStart);

	fp = fopen("F:\install.txt", "r");

	QueryPerformanceCounter(&liPerfNow);

	long time = (((liPerfNow.QuadPart - m_liPerfStart.QuadPart) * 1000000) / m_liPerfFreq.QuadPart);
	printf("%ld microseconds\n", time);
	fclose(fp);

	//time to read context from main memory
	int a[400][400] = { 0 } ,b[400];
	printf("Time to read the context from main memory is ");
	m_liPerfFreq = { 0 };
	QueryPerformanceFrequency(&m_liPerfFreq);

	m_liPerfStart = { 0 }, liPerfNow = { 0 };
	QueryPerformanceCounter(&m_liPerfStart);
	for(int i = 0; i < 400; i++) {
		b[i] = a[i][0];
	}
	
	QueryPerformanceCounter(&liPerfNow);

	time = (((liPerfNow.QuadPart - m_liPerfStart.QuadPart) * 2500000) / m_liPerfFreq.QuadPart);
	printf("%ld nanoseconds\n", time);

	//time to read context from cache 
	printf("Time to read the context from cache is ");
	m_liPerfFreq = { 0 };
	QueryPerformanceFrequency(&m_liPerfFreq);

	m_liPerfStart = { 0 }, liPerfNow = { 0 };
	QueryPerformanceCounter(&m_liPerfStart);
	for (int i = 0; i < 400; i++) {
		b[i] = a[399][i];
	}
	QueryPerformanceCounter(&liPerfNow);

	time = (((liPerfNow.QuadPart - m_liPerfStart.QuadPart) * 2500000) / m_liPerfFreq.QuadPart);
	printf("%ld nanoseconds\n", time);

    return 0;
}

