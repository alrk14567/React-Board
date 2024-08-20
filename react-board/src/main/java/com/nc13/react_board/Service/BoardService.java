package com.nc13.react_board.Service;

import com.nc13.react_board.model.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BoardService {
    private final SqlSession SESSION;
    private final String NAMESPACE = "mapper.BoardMapper";
    private final int PAGE_SIZE = 20;

    @Autowired
    public BoardService(SqlSession session) {
        SESSION = session;
    }

    public BoardDTO selectOne(int id) {
        return SESSION.selectOne(NAMESPACE + ".selectOne", id);
    }

    public List<BoardDTO> selectAll(int pageNo) {
        HashMap<String, Integer> paramMap = new HashMap<>();
        paramMap.put("startRow", (pageNo - 1) * PAGE_SIZE);
        paramMap.put("size", PAGE_SIZE);

        return SESSION.selectList(NAMESPACE + ".selectList",paramMap);
    }

    public int selectMaxPage() {
        int maxRow = SESSION.selectOne(NAMESPACE + ".selectMaxPage");
        int maxPage=maxRow/PAGE_SIZE;
        //자바에서 삼항연산자의 사용은 값은 할당 할때 사용해야지 단독으로는 사용 x
        maxPage = (maxRow % PAGE_SIZE == 0) ? maxPage : maxPage + 1;

        return maxPage;
    }

    public void insert(BoardDTO boardDTO){
        SESSION.insert(NAMESPACE+".insert",boardDTO);
    }

    public void update(BoardDTO boardDTO) {
        SESSION.update(NAMESPACE+".update",boardDTO);
    }

    public void delete(int id) {
        SESSION.delete(NAMESPACE+".delete",id);
    }
}
