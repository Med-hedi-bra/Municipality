package com.example.mini_projet.Statistics;

import com.example.mini_projet.Municipality.MunicipalityRepository;
import com.example.mini_projet.Municipality.MunicipalityService;
import com.example.mini_projet.Municipality.Municipality;
import com.example.mini_projet.Post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {


    final StatisticsRepository statisticsRepository;
    final MunicipalityService municipalityService;
    final MunicipalityRepository municipalityRepository;



    public List<Statistics> getAll() {
        return statisticsRepository.findAll();
    }



    public Statistics getById(Long idStat)
    {
        Optional<Statistics> statistics =  statisticsRepository.findById(idStat);
        if(statistics.isPresent())  return statistics.get();
        else throw new IllegalStateException("Statisitics not exist");
    }

    public Optional<Statistics> getStatByIdMun(@PathVariable Long codeMun){
        List<Statistics> stats = statisticsRepository.findAll();
        Optional<Statistics> statByMun =
                stats.stream().filter(stat -> stat.getMunicipality().equals(municipalityRepository.findById(codeMun).get()))
                        .findFirst();
        return statByMun;
    }



    public boolean insertStat(Statistics s , Long idMun) {
        try {
            Municipality municipality= municipalityRepository.findByCodeMuni(idMun);
            s.setMunicipality(municipality);
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
