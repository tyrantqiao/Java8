package com.qiao.java8.data;

import java.util.Optional;

/**
 * @author qiao
 * email: tyrantqiao@icloud.com
 * time: 2017/9/20 18:03
 */
public abstract class ProcessingObject<T> {
	protected ProcessingObject processor;
	Optional<String> s=Optional.empty();
	public void setProcessor(ProcessingObject<T> processor){
		this.processor=processor;
	}

	public T handle(T input){
		T r=handleWork(input);
		if(processor!=null)
			return (T) processor.handle(r);
		return r;
	}

	abstract protected T handleWork(T input);
}
