package lt.vu.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.Arrays;

@Interceptor
@LoggedDeletion
public class DeletionLogger implements Serializable {
    @AroundInvoke
    public Object logObjectDeletion(InvocationContext context) throws Exception {
        System.out.println("Deleting entity in method: " + context.getMethod().getName());
        System.out.println("Parameters: " + Arrays.toString(context.getParameters()));
        return context.proceed();
    }
}
