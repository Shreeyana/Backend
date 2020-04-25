package nce.majorproject.context;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextHolder implements Runnable{

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<>();
    private final Long userId;
    private final String username;
    private final String fullName;
    private final String userType;

    public ContextHolder(Long userId, String username,String userType,String fullName) {
        this.userId = userId;
        this.username = username;
        this.fullName=fullName;
        this.userType=userType;
    }

    public static void set(Context context) {
        threadLocal.set(context);
    }

    public static void unset() {
        threadLocal.remove();
    }

    public static Context get() {
        return threadLocal.get();
    }
    @Override
    public void run() {
        set(new Context(userId, userType,username,fullName));
        new ContextHolderServices().getContext();
    }
}
