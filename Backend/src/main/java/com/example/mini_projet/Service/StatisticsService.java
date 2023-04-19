package com.example.mini_projet.Service;

import com.example.mini_projet.Repositories.StatisticsRepository;
import com.example.mini_projet.models.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    final StatisticsRepository statisticsRepository;



    public List<Statistics> getAll() {
        return statisticsRepository.findAll();
    }

    public Statistics getById(Long id) {
        Optional<Statistics> statistics =  statisticsRepository.findById(id);
        if(statistics.isPresent())  return statistics.get();
        else throw new IllegalStateException("Statisitics not exist");
    }

    public boolean insert(Statistics s) {

        try {
            statisticsRepository.save(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateStatistics(Long id, Statistics s) {
        Optional<Statistics> statistics = statisticsRepository.findById(id);

        Integer population = s.getPopulation();
        Integer surface = s.getSurface();
        Integer age = s.getAge();
        Integer program = s.getProgram();
        if (statistics.isEmpty()) return false;
        if (population != null && !Objects.equals(population, statistics.get().getPopulation())) {
            statistics.get().setPopulation(population);
        }
        if ( surface != null && !Objects.equals(surface, statistics.get().getSurface())) {
            statistics.get().setSurface(surface);
        }
        if ( age != null && !Objects.equals(age, statistics.get().getAge())) {
            statistics.get().setAge(age);
        }
        if ( program != null && !Objects.equals(program, statistics.get().getProgram())) {
            statistics.get().setProgram(program);
        }

        return true;
    }

    public boolean deleteById(Long id) {
        Optional<Statistics> statistics = statisticsRepository.findById(id);
        if (statistics.isEmpty()) return false;
        statisticsRepository.deleteById(id);
        return true;
    }


}
