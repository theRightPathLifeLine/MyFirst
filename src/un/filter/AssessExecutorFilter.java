package un.filter;

import org.apache.mina.filter.executor.ExecutorFilter;

import java.util.concurrent.Executors;

/**
 * @author gonglei
 */
public class AssessExecutorFilter extends ExecutorFilter {

    public AssessExecutorFilter() {
        super(Executors.newCachedThreadPool());
    }
}
