import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Component
public class UserProcessor {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void processAndQueueData(BlockingQueue<UserClassList> queue) {
        try {
            // Fetch the next session ID based on order by id and queueFlag = 'N'
            String sessionId = userRepository.findNextSessionIdOrderByUserId();

            // Fetch the first 25 rows for the obtained session ID
            List<UserClass> userData = userRepository.findNext25RowsForSessionId(sessionId);

            // Update queueFlag in the database for the fetched rows
            updateQueueFlagInDatabase(userData);

            // Create UserClassList and add it to the queue
            UserClassList userClassList = new UserClassList(userData);
            queue.put(userClassList);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Handle interruption
        }
    }

    @Transactional
    private void updateQueueFlagInDatabase(List<UserClass> userData) {
        for (UserClass user : userData) {
            // Update the queueFlag column in the database for each user
            user.setQueueFlag("Y");
            userRepository.save(user);
        }
    }
}
