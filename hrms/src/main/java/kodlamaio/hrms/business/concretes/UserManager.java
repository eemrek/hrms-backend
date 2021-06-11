package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataaccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        super();
        this.userDao = userDao;
    }


    @Override
    public DataResult<List<User>> getAll() {
       return new SuccessDataResult<List<User>>(this.userDao.findAll(),"Kullanıcı listesi");
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessDataResult<List<User>>("Kullanıcı eklendi");
    }
}