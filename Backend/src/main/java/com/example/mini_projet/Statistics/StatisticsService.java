package com.example.mini_projet.Statistics;

import com.example.mini_projet.Municipality.MunicipalityService;
import com.example.mini_projet.Municipality.Municipality;
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
    final MunicipalityService municipalityService;



    public List<Statistics> getAll() {
        return statisticsRepository.findAll();
    }

    public Statistics getById(Long id) {
        Optional<Statistics> statistics =  statisticsRepository.findById(id);
        if(statistics.isPresent())  return statistics.get();
        else throw new IllegalStateException("Statisitics not exist");
    }

    public boolean insert(Statistics s , Long idMun) {

        try {
            Municipality municipality= municipalityService.getById(idMun);
            s.setMunicipality(municipality);
            statisticsRepository.save(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Statistics getByIdMunicipality(Long id){
        return statisticsRepository.getByMunicipalityId(id);
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

    public boolean deleteAll(){
        try {
            statisticsRepository.deleteAll();
            return true;
        }
        catch (Exception e){
            return false;
        }


    }


}
